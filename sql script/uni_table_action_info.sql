CREATE TABLE UNI_PRIMARY.UNI_ACTION_INFO (
	PK_UNI_ACTION_INFO INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ACTION_NAME VARCHAR(100) NOT NULL,
	DATE_CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CREATED_BY VARCHAR(100) DEFAULT 'suepr_uni',
	DATE_UPDATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	UPDATED_BY VARCHAR(100) DEFAULT 'suepr_uni'
);

GRANT SELECT, INSERT, UPDATE, DELETE ON UNI_PRIMARY.UNI_ACTION_INFO TO 'uni_dev'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON UNI_PRIMARY.UNI_ACTION_INFO TO 'uni_dml'@'localhost';
GRANT SELECT ON UNI_PRIMARY.UNI_ACTION_INFO TO 'uni_qry'@'localhost';