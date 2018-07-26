create index IX_D73F240A on APIO_Recipe (companyId);
create index IX_9975250C on APIO_Recipe (groupId);
create index IX_684E1FD2 on APIO_Recipe (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_282072D4 on APIO_Recipe (uuid_[$COLUMN_LENGTH:75$], groupId);