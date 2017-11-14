--char max size entered, varchar the size of the value entered not the maximum size
use star;
create table date_dim (datekey int primary key,
						[day] datetime, 
						day_of_week char(1), --first day sunday
						type_of_day bit, --0=weekend, 1=weekday
						calendar_week varchar(1), --week number 1-4
						calendar_month varchar(2), --month number 1-12
						calendar_quarter char(1), --1 to 4
						calendar_year char(4), --year value as in date
						fiscal_week varchar(3),
						fiscal_quarter char(1), 
						fiscal_month varchar(2),
						fiscal_year char(4)
					);
