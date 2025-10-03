from SPARQLWrapper import SPARQLWrapper, JSON
import pandas as pd

## Create a SPARQL endpoint
sparql = SPARQLWrapper("https://dbpedia.org/sparql")
sparql.setReturnFormat(JSON)

## Create the query
query = '''
SELECT ?person ?personLabel ?birthPlace ?birthPlaceLabel
WHERE{
    ?person a dbo:Person;
            dbo:birthPlace ?birthPlace.
    ?person rdfs:label ?personLabel.
    ?birthPlace rdfs:label ?birthPlaceLabel.
    FILTER (lang(?personLabel) = "en")
    FILTER (lang(?birthPlaceLabel) = "en")
}
LIMIT 200
'''

## Send the query to the endpoint
sparql.setQuery(query=query)

## Get the results
results = sparql.query().convert()

## Parse through the results
data = []
for res in results["results"]["bindings"]:
    data.append({
        "Person": res["personLabel"]["value"],
        "BirthPlace": res["birthPlaceLabel"]["value"]
    })
    
df = pd.DataFrame(data)
print(df.head())