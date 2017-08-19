SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS members (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  contact VARCHAR,
  memId int

  );

  CREATE TABLE IF NOT EXISTS bouquets (
  id int PRIMARY KEY auto_increment,
  teamName VARCHAR,
  teamDesc VARCHAR

  );