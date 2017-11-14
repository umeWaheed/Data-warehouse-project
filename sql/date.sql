
DECLARE @startdate DATETIME = '01/01/1993'; --mm/dd/yy
declare @enddate DATETIME = '01/01/1999';
--DECLARE @startdate DATETIME = '01/01/1993';
--DECLARE @enddate DATETIME ='01/01/1999';

declare 
	--@dayofweekinmonth int,
	--@dayofweekinyear int,
	--@dayofquarter int,
	@weekofmonth int,
	@currentyear int,
	@currentmonth int,
	@currentquarter int

DECLARE @DayOfWeek TABLE (DOW INT, MonthCount INT, QuarterCount INT, YearCount INT)

INSERT INTO @DayOfWeek VALUES (1, 0, 0, 0);
INSERT INTO @DayOfWeek VALUES (2, 0, 0, 0);
INSERT INTO @DayOfWeek VALUES (3, 0, 0, 0);
INSERT INTO @DayOfWeek VALUES (4, 0, 0, 0);
INSERT INTO @DayOfWeek VALUES (5, 0, 0, 0);
INSERT INTO @DayOfWeek VALUES (6, 0, 0, 0);
INSERT INTO @DayOfWeek VALUES (7, 0, 0, 0);

DECLARE @CurrentDate AS DATETIME = @StartDate
SET @CurrentMonth = DATEPART(MM, @CurrentDate)
SET @CurrentYear = DATEPART(YY, @CurrentDate)
SET @CurrentQuarter = DATEPART(QQ, @CurrentDate)

select @CurrentDate as dat,@CurrentMonth as mont,@CurrentYear as yr,@CurrentQuarter as quart 

WHILE @CurrentDate < @EndDate
BEGIN
 
--/*Begin day of week logic*/

--         /*Check for Change in Month of the Current date if Month changed then 
--          Change variable value*/
	IF @CurrentMonth != DATEPART(MM, @CurrentDate) 
	BEGIN
		UPDATE @DayOfWeek
		SET MonthCount = 0
		SET @CurrentMonth = DATEPART(MM, @CurrentDate)
	END

--        /* Check for Change in Quarter of the Current date if Quarter changed then change 
--         Variable value*/

	IF @CurrentQuarter != DATEPART(QQ, @CurrentDate)
	BEGIN
		UPDATE @DayOfWeek
		SET QuarterCount = 0
		SET @CurrentQuarter = DATEPART(QQ, @CurrentDate)
	END
       
--        /* Check for Change in Year of the Current date if Year changed then change 
--         Variable value*/
	

	IF @CurrentYear != DATEPART(YY, @CurrentDate)
	BEGIN
		UPDATE @DayOfWeek
		SET YearCount = 0
		SET @CurrentYear = DATEPART(YY, @CurrentDate)
	END
	
--        -- Set values in table data type created above from variables 

	UPDATE @DayOfWeek
	SET 
		MonthCount = MonthCount + 1,
		QuarterCount = QuarterCount + 1,
		YearCount = YearCount + 1
	WHERE DOW = DATEPART(DW, @CurrentDate)

--	SELECT
--		@DayOfWeekInMonth = MonthCount,
--		@DayOfQuarter = QuarterCount,
--		@DayOfWeekInYear = YearCount
--	FROM @DayOfWeek
--	WHERE DOW = DATEPART(DW, @CurrentDate)
	
--/*End day of week logic*/


--/* Populate Your Dimension Table with values*/
	use star;
	INSERT INTO date_dim (datekey,[day],day_of_week,type_of_day,calendar_week,calendar_month,calendar_quarter,calendar_year)
	SELECT
		
		CONVERT (char(8),@CurrentDate,112) as datekey,
		@CurrentDate AS day,
--		CONVERT (char(10),@CurrentDate,103) as FullDateUK,
--		CONVERT (char(10),@CurrentDate,101) as FullDateUSA,
--		DATEPART(DD, @CurrentDate) AS DayOfMonth,
--		--Apply Suffix values like 1st, 2nd 3rd etc..
--		CASE 
--			WHEN DATEPART(DD,@CurrentDate) IN (11,12,13) _
--			THEN CAST(DATEPART(DD,@CurrentDate) AS VARCHAR) + 'th'
--			WHEN RIGHT(DATEPART(DD,@CurrentDate),1) = 1 _
--			THEN CAST(DATEPART(DD,@CurrentDate) AS VARCHAR) + 'st'
--			WHEN RIGHT(DATEPART(DD,@CurrentDate),1) = 2 _
--			THEN CAST(DATEPART(DD,@CurrentDate) AS VARCHAR) + 'nd'
--			WHEN RIGHT(DATEPART(DD,@CurrentDate),1) = 3 _
--			THEN CAST(DATEPART(DD,@CurrentDate) AS VARCHAR) + 'rd'
--			ELSE CAST(DATEPART(DD,@CurrentDate) AS VARCHAR) + 'th' 
--			END AS DaySuffix,
		
--		DATENAME(DW, @CurrentDate) AS DayName,
		DATEPART(DW, @CurrentDate) AS day_of_week,
		CASE DATEPART(DW, @CurrentDate)
			WHEN 1 THEN 0
			WHEN 2 THEN 1
			WHEN 3 THEN 1
			WHEN 4 THEN 1
			WHEN 5 THEN 1
			WHEN 6 THEN 1
			WHEN 7 THEN 0
			END AS type_of_day,
--		
--		-- check for day of week as Per US and change it as per UK format 
--		CASE DATEPART(DW, @CurrentDate)
--			WHEN 1 THEN 7
--			WHEN 2 THEN 1
--			WHEN 3 THEN 2
--			WHEN 4 THEN 3
--			WHEN 5 THEN 4
--			WHEN 6 THEN 5
--			WHEN 7 THEN 6
--			END 
--			AS DayOfWeekUK,
		
--		@DayOfWeekInMonth AS DayOfWeekInMonth,
--		@DayOfWeekInYear AS DayOfWeekInYear,
--		@DayOfQuarter AS DayOfQuarter,
--		DATEPART(DY, @CurrentDate) AS DayOfYear,
		DATEPART(WW, @CurrentDate) + 1 - DATEPART(WW, CONVERT(VARCHAR,  DATEPART(MM, @CurrentDate)) + '/1/' + CONVERT(VARCHAR,  DATEPART(YY, @CurrentDate))) AS WeekOfMonth,
--		(DATEDIFF(DD, DATEADD(QQ, DATEDIFF(QQ, 0, @CurrentDate), 0), _
--		@CurrentDate) / 7) + 1 AS WeekOfQuarter,
--		DATEPART(WW, @CurrentDate) AS WeekOfYear,
		DATEPART(MM, @CurrentDate) AS calendar_month,
--		DATENAME(MM, @CurrentDate) AS MonthName,
--		CASE
--			WHEN DATEPART(MM, @CurrentDate) IN (1, 4, 7, 10) THEN 1
--			WHEN DATEPART(MM, @CurrentDate) IN (2, 5, 8, 11) THEN 2
--			WHEN DATEPART(MM, @CurrentDate) IN (3, 6, 9, 12) THEN 3
--			END AS MonthOfQuarter,
		DATEPART(QQ, @CurrentDate) AS calendar_quarter,
--		CASE DATEPART(QQ, @CurrentDate)
--			WHEN 1 THEN 'First'
--			WHEN 2 THEN 'Second'
--			WHEN 3 THEN 'Third'
--			WHEN 4 THEN 'Fourth'
--			END AS QuarterName,
		DATEPART(YEAR, @CurrentDate) AS calendar_year
--		'CY ' + CONVERT(VARCHAR, DATEPART(YEAR, @CurrentDate)) AS YearName,
--		LEFT(DATENAME(MM, @CurrentDate), 3) + '-' + CONVERT(VARCHAR, _
--		DATEPART(YY, @CurrentDate)) AS MonthYear,
--		RIGHT('0' + CONVERT(VARCHAR, DATEPART(MM, @CurrentDate)),2) + _
--		CONVERT(VARCHAR, DATEPART(YY, @CurrentDate)) AS MMYYYY,
--		CONVERT(DATETIME, CONVERT(DATE, DATEADD(DD, - (DATEPART(DD, _
--		@CurrentDate) - 1), @CurrentDate))) AS FirstDayOfMonth,
--		CONVERT(DATETIME, CONVERT(DATE, DATEADD(DD, - (DATEPART(DD, _
--		(DATEADD(MM, 1, @CurrentDate)))), DATEADD(MM, 1, _
--		@CurrentDate)))) AS LastDayOfMonth,
--		DATEADD(QQ, DATEDIFF(QQ, 0, @CurrentDate), 0) AS FirstDayOfQuarter,
--		DATEADD(QQ, DATEDIFF(QQ, -1, @CurrentDate), -1) AS LastDayOfQuarter,
--		CONVERT(DATETIME, '01/01/' + CONVERT(VARCHAR, DATEPART(YY, _
--		@CurrentDate))) AS FirstDayOfYear,
--		CONVERT(DATETIME, '12/31/' + CONVERT(VARCHAR, DATEPART(YY, _
--		@CurrentDate))) AS LastDayOfYear,
--		NULL AS IsHolidayUSA,
--		NULL AS HolidayUSA, Null, Null

	SET @CurrentDate = DATEADD(DD, 1, @CurrentDate)
END

DECLARE
	@dtFiscalYearStart SMALLDATETIME = 'January 01, 1993',
	@FiscalYear INT = 1993,
	@LastYear INT = 1999,
	@FirstLeapYearInPeriod INT = 1996 --keep same

DECLARE
	@iTemp INT,
	@LeapWeek INT,
	@Current DATETIME,
	@FiscalDayOfYear INT,
	@FiscalWeekOfYear INT,
	@FiscalMonth INT,
	@FiscalQuarter INT,
	--@FiscalQuarterName VARCHAR(10),
	--@FiscalYearName VARCHAR(7),
	@LeapYear INT
	--@FiscalFirstDayOfYear DATE,
	--@FiscalFirstDayOfQuarter DATE,
	--@FiscalFirstDayOfMonth DATE,
	--@FiscalLastDayOfYear DATE,
	--@FiscalLastDayOfQuarter DATE,
	--@FiscalLastDayOfMonth DATE

/*Holds the years that have 455 in last quarter*/

DECLARE @LeapTable TABLE (leapyear INT)

/*TABLE to contain the fiscal year calendar*/

DECLARE @tb TABLE(
	PeriodDate DATETIME,
	[FiscalDayOfYear] VARCHAR(3),
	[FiscalWeekOfYear] VARCHAR(3),
	[FiscalMonth] VARCHAR(2), 
	[FiscalQuarter] VARCHAR(1),
	--[FiscalQuarterName] VARCHAR(9),
	[FiscalYear] VARCHAR(4)
	--[FiscalYearName] VARCHAR(7),
	--[FiscalMonthYear] VARCHAR(10),
	--[FiscalMMYYYY] VARCHAR(6),
	--[FiscalFirstDayOfMonth] DATE,
	--[FiscalLastDayOfMonth] DATE,
	--[FiscalFirstDayOfQuarter] DATE,
	--[FiscalLastDayOfQuarter] DATE,
	--[FiscalFirstDayOfYear] DATE,
	--[FiscalLastDayOfYear] DATE
	)

/*Populate the table with all leap years*/

SET @LeapYear = @FirstLeapYearInPeriod
WHILE (@LeapYear < @LastYear)
	BEGIN
		INSERT INTO @leapTable VALUES (@LeapYear)
		SET @LeapYear = @LeapYear + 5
	END

/*Initiate parameters before loop*/

SET @CurrentDate = @dtFiscalYearStart
SET @FiscalDayOfYear = 1
SET @FiscalWeekOfYear = 1
SET @FiscalMonth = 1
SET @FiscalQuarter = 1
SET @FiscalWeekOfYear = 1

IF (EXISTS (SELECT * FROM @LeapTable WHERE @FiscalYear = leapyear))
	BEGIN
		SET @LeapWeek = 1
	END
	ELSE
	BEGIN
		SET @LeapWeek = 0
	END

/*******************************************************************************************/

/* Loop on days in interval*/

WHILE (DATEPART(yy,@CurrentDate) <= @LastYear)
BEGIN
	
/*SET fiscal Month*/
	SELECT @FiscalMonth = CASE 
		/*Use this section for a 4-5-4 calendar.  
		Every leap year the result will be a 4-5-5*/
		WHEN @FiscalWeekOfYear BETWEEN 1 AND 4 THEN 1 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 5 AND 9 THEN 2 /*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 10 AND 13 THEN 3 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 14 AND 17 THEN 4 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 18 AND 22 THEN 5 /*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 23 AND 26 THEN 6 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 27 AND 30 THEN 7 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 31 AND 35 THEN 8 /*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 36 AND 39 THEN 9 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 40 AND 43 THEN 10 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 44 AND (48+@LeapWeek) THEN 11 
/*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN (49+@LeapWeek) AND (52+@LeapWeek) THEN 12 
/*4 weeks (5 weeks on leap year)*/
		
/*Use this section for a 4-4-5 calendar.  
Every leap year the result will be a 4-5-5*/
		/*
		WHEN @FiscalWeekOfYear BETWEEN 1 AND 4 THEN 1 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 5 AND 8 THEN 2 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 9 AND 13 THEN 3 /*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 14 AND 17 THEN 4 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 18 AND 21 THEN 5 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 22 AND 26 THEN 6 /*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 27 AND 30 THEN 7 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 31 AND 34 THEN 8 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 35 AND 39 THEN 9 /*5 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 40 AND 43 THEN 10 /*4 weeks*/
		WHEN @FiscalWeekOfYear BETWEEN 44 AND _
		(47+@leapWeek) THEN 11 /*4 weeks (5 weeks on leap year)*/
WHEN @FiscalWeekOfYear BETWEEN (48+@leapWeek) AND (52+@leapWeek) THEN 12 /*5 weeks*/
		*/
	END

	/*SET Fiscal Quarter*/
	SELECT @FiscalQuarter = CASE 
		WHEN @FiscalMonth BETWEEN 1 AND 3 THEN 1
		WHEN @FiscalMonth BETWEEN 4 AND 6 THEN 2
		WHEN @FiscalMonth BETWEEN 7 AND 9 THEN 3
		WHEN @FiscalMonth BETWEEN 10 AND 12 THEN 4
	END
	
	--SELECT @FiscalQuarterName = CASE 
	--	WHEN @FiscalMonth BETWEEN 1 AND 3 THEN 'First'
	--	WHEN @FiscalMonth BETWEEN 4 AND 6 THEN 'Second'
	--	WHEN @FiscalMonth BETWEEN 7 AND 9 THEN 'Third'
	--	WHEN @FiscalMonth BETWEEN 10 AND 12 THEN 'Fourth'
	--END
	
	/*Set Fiscal Year Name*/
	--SELECT @FiscalYearName = 'FY ' + CONVERT(VARCHAR, @FiscalYear)

	INSERT INTO @tb (PeriodDate, FiscalWeekOfYear,
	fiscalMonth, FiscalQuarter, FiscalYear) VALUES 
	(@CurrentDate, @FiscalWeekOfYear, @FiscalMonth, 
	@FiscalQuarter, @FiscalYear)

	/*SET next day*/
	SET @CurrentDate = DATEADD(dd, 1, @CurrentDate)
	SET @FiscalDayOfYear = @FiscalDayOfYear + 1
	SET @FiscalWeekOfYear = ((@FiscalDayOfYear-1) / 7) + 1


	IF (@FiscalWeekOfYear > (52+@LeapWeek))
	BEGIN
		/*Reset a new year*/
		SET @FiscalDayOfYear = 1
		SET @FiscalWeekOfYear = 1
		SET @FiscalYear = @FiscalYear + 1
		IF ( EXISTS (SELECT * FROM @leapTable WHERE @FiscalYear = leapyear))
		BEGIN
			SET @LeapWeek = 1
		END
		ELSE
		BEGIN
			SET @LeapWeek = 0
		END
	END
END

UPDATE date_dim
	SET
	  fiscal_week = a.FiscalWeekOfYear
	, fiscal_month = a.FiscalMonth
	, fiscal_quarter = a.FiscalQuarter
	--, FiscalQuarterName = a.FiscalQuarterName
	, fiscal_year = a.FiscalYear
	--, FiscalYearName = a.FiscalYearName
	--, FiscalMonthYear = a.FiscalMonthYear
	--, FiscalMMYYYY = a.FiscalMMYYYY
	--, FiscalFirstDayOfMonth = a.FiscalFirstDayOfMonth
	--, FiscalLastDayOfMonth = a.FiscalLastDayOfMonth
	--, FiscalFirstDayOfQuarter = a.FiscalFirstDayOfQuarter
	--, FiscalLastDayOfQuarter = a.FiscalLastDayOfQuarter
	--, FiscalFirstDayOfYear = a.FiscalFirstDayOfYear
	--, FiscalLastDayOfYear = a.FiscalLastDayOfYear
FROM @tb a
	INNER JOIN date_dim b ON a.PeriodDate = b.[day]

