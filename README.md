noark-web-services-prototype
============================

### Prerequisites

1. Download and unpack [db4o](http://www.db4o.com/downloads/db4o-8.0-java.zip)

2. Install the db4o-*-all-java5.jar file in the local Maven repository:

        mvn install:install-file -Dfile=db4o-8.0.249.16098-all-java5.jar -DgroupId=db4o \
        -DartifactId=db4o -Dversion=8.0.249 -Dpackaging=jar

3. Install a JSON formatting plugin for your browser, e.g. [JSONView for Firefox](https://addons.mozilla.org/en-US/firefox/addon/jsonview/)

### Build and run

    cd /path/to/noark.web.service && mvn clean package
    
    java -jar target/noark.web.service-1.0-SNAPSHOT.jar
    
### Notes

1. The web service is available at http://localhost:8080/noark/.

2. To purge all available data, visit http://localhost:8080/noark/cleanup.

3. The prototype service demonstrates how certain capabilities could be implemented in general and does not try to follow the Noark data model closely. The relationships in the model are as follows:

          [Record] * -- 1 [Case] * -- * [Party]

4. Search functionality is based on [FIQL](https://tools.ietf.org/html/draft-nottingham-atompub-fiql-00) (see also [RSQL](https://github.com/jirutka/rsql-parser)). Note that only a very small subset of FIQL is implemented in this prototype. A few examples follow:

 Search for cases whose title starts with _Case_ :

 http://localhost:8080/noark/cases?search=caseTitle==Case* 
 
 Search for cases whose title starts with _Case_ which have a record(s) with title _Record 1_ :
 
 http://localhost:8080/noark/cases?search=caseTitle==Case*;recordTitle==Record%201
 
 Search for cases that have a party whose title starts with _Jane_ :
 
 http://localhost:8080/noark/cases?search=partyName==Jane*
 
 Search for records whose title starts with _Record_ :
 
 http://localhost:8080/noark/records?search=recordTitle==Record*
 
 Note that searching for records based on properties of other objects is not implemented.
 
 Search for parties whose title starts with _John_ :
 
 http://localhost:8080/noark/parties?search=partyName==John*
 
 Note that searching for parties based on properties of other objects is not implemented.










