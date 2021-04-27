CREATE TABLES :
	
	CREATE TABLE associate(
	   sono serial PRIMARY KEY,
	   associate_name VARCHAR (50) UNIQUE NOT NULL,
	   associateid INTEGER NOT NULL,
	   associate_email_id VARCHAR (355) NOT NULL,
	   gender VARCHAR (50),
	   technology VARCHAR (355) NOT NULL,
	   experience INTEGER NOT NULL
	);
	
INSERT VALUES :	

	INSERT INTO 
		associate(sono, associate_name, associateid, associate_email_id, gender, technology, experience) 
	VALUES
		(1, 'Srikanth', 123, 'sj@yahoo.in', 'Male', 'Java', 5);