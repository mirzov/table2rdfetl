#query = paste("PREFIX dm: <http://www.canmove.lu.se/data/prototype/datamodel/>",
#					"select ?name ?proj where{",
#					"?person dm:worksOn [rdfs:label ?proj] .",
#					"?person dm:name ?name",
#				"} order by ?name", sep="\n")
query = paste("PREFIX dm: <http://www.canmove.lu.se/data/prototype/datamodel/>",
					"select ?alt ?weight where{",
					"?meas dm:maxAltitude ?alt .",
					"?meas dm:hasSubject ?subj .",
					"?subj dm:weight ?weight .",
				"}", sep="\n")
fullUrl = paste("http://127.0.0.1:9000/sparql?query=", URLencode(query), sep="")
tblConn = url(fullUrl, "r", TRUE, "utf-8")
tbl = read.delim(tblConn)
close(tblConn)
rm(query, fullUrl, tblConn)
maxAltitude = tbl[["alt"]]
weight = tbl[["weight"]]
View(tbl)
plot(weight, maxAltitude)
