#query = paste("PREFIX dm: <http://www.canmove.lu.se/data/prototype/datamodel/>",
#								"select ?name where{",
#								"?s dm:name ?name .",
#							"}", sep="\n")
query = paste("PREFIX dm: <http://www.canmove.lu.se/data/prototype/datamodel/>",
								"select ?name ?proj where{",
								"?person <http://www.canmove.lu.se/data/prototype/datamodel/worksOn> [rdfs:label ?proj] .",
								"?person dm:name ?name",
							"} order by ?name", sep="\n")
fullUrl = paste("http://127.0.0.1:9000/sparql?query=", URLencode(query), sep="")
tblConn = url(fullUrl, "r", TRUE, "utf-8")
tbl = read.delim(tblConn)
close(tblConn)
rm(query, fullUrl, tblConn)
View(tbl)
