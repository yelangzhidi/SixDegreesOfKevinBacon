# SixDegreesOfKevinBacon
CS 245 Assignment 2
# CS245A2.java
main class<br />
program argument: tmdb_5000_credits.csv filepath<br />
Actor 1 name: ​David Guy Brizan<br />
No such actor.<br />
Actor 1 name: ​Hailee Steinfeld<br />
Actor 2 name: ​Abigail Breslin<br />
Path between Hailee Steinfeld and Abigail Breslin: Hailee Steinfeld --> Abigail Breslin<br />
Continue? [Y/N] ​Y<br />
Actor 1 name:​ Asa Butterfield<br />
Actor 2 name:​ Paul Dano<br />
Path between Asa Butterfield and Paul Dano: Asa Butterfield --> Viola Davis --> Paul Dano<br />
Continue? [Y/N] ​N<br />
<br />
case sensitive: regarless the uppercase/lower case.<br />
# TableGraph.java
Analyze Running time<br />
addEdges(String[] actors): worstcase: O(n^2) where n is the actor number in actors.<br />
findShortestRoute(String src, String dest): worstcase: O(V * E) where V is the actors in Graph, and E is each actors have how many relations.
