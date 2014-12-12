create DATABASE batmeca;
USE mysql;
CREATE USER bat@'localhost' IDENTIFIED BY 'bat';
GRANT ALL PRIVILEGES on batmeca.* to bat@'%';
FLUSH PRIVILEGES;
