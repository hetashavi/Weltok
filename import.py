import csv 
from cassandra.cluster import Cluster

#Connect to the cluster

cluster = Cluster()
session = cluster.connect('welltok')
session.execute('USE welltok')
#session.execute('create table welltok.data (Id int Primary key,title text,description text,author text,tags text,created_at date,updated_at date)')

	
with open('import.csv') as csvfile:
  reader = csv.DictReader(csvfile)
  for row in reader:
	session.execute("INSERT INTO welltok.data (id,title,description ,author ,tags ,created_at ,updated_at ) VALUES (%s,%s,%s,%s,%s,%s,%s)", [int(row['Id']),row['title'],row['description'],row['author'],row['tags'],row['created_at'],row['updated_at']])
	
#Verify All rows are populated
rows = session.execute('SELECT id FROM data')
for user_row in rows:
	print user_row.id
	