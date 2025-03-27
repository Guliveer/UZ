ALTER TABLE studenci DROP COLUMN typ_uczel_id;
ALTER TABLE studenci CHANGE COLUMN stud_id student_id INT;
ALTER TABLE studenci ADD COLUMN data_urodzenia DATE;
ALTER TABLE studenci ADD COLUMN plec CHAR(1);
