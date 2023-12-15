Didn't extensively collaborate with anyone other than ask for guidance from professors and TAs

Bibliography:

https://neo4j.com/docs/graph-data-science/current/algorithms/wcc/#:~:text=The%20Weakly%20Connected%20Components%20(WCC,each%20other%20form%20a%20component.

https://en.wikipedia.org/wiki/Strongly_connected_component#:~:text=A%20directed%20graph%20is%20called,second%20vertex%20to%20the%20first.

https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

https://www.baeldung.com/java-dijkstra

https://www.youtube.com/watch?v=XEb7_z5dG3c

https://www.simplilearn.com/tutorials/java-tutorial/reverse-a-string-in-java

https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm


Journal:

PHASE 0
DAY 1 - 11/30/23
Eva & Musa
7:00 PM - 8:00 PM
Progress:
 - Picked dataset
 - Filled in required information in data_description text file
 - Copy/pasted dataset into new text file
 - Attempted to run Main file, recieved error
Questions:
 - What does 'WCC' stand for? (i.e. "Nodes in largest WCC:")
Weakly Connected Components algorithm finds sets of connected nodes in directed and undirected graphs. Two nodes are connected, if there exists a path between them. The set of all nodes that are connected with each other form a component.
Source: https://neo4j.com/docs/graph-data-science/current/algorithms/wcc/#:~:text=The%20Weakly%20Connected%20Components%20(WCC,each%20other%20form%20a%20component.

 - What does 'SCC' stand for? (i.e. "Nodes in largest SCC:")
Strongly Connected Components algorithm is when there is a path in each direction between each pair of vertices of the graph.
Source: https://en.wikipedia.org/wiki/Strongly_connected_component#:~:text=A%20directed%20graph%20is%20called,second%20vertex%20to%20the%20first.

 - Should we delete the _MACOSX file in the GraphDisplay file? Does it only exist as a MacOS alternative for the graph display?
Yes, but no longer relevant due to separate Gradle file

 - Why is Main returning an error when attempting to run? Error: "Unsupported operation. Please use pom.xml file to manage the source directories of maven project."
Probably because we did not have Gradle installed; no longer relvant due to separate Gradle file

 - How do we figure out how to interpret the values in our dataset when the website doesn't clarify what values exactly mean what?
Can use context clues for existence of nodes (i. e. a path between a Node A and Node B implies existence of a Node A and B). Making a graph is still possible due to the two columns of numbers repsenting the path between nodes (i.e the first number in line 5 is showing Node 0, which has a path to Node 1), we just won't know where in Texas these roads are. Maybe look at the paper for more context?

PHASE 1
DAY 2 - 12/4/23
Eva & Musa
7:00 PM - 9:00 PM
Progress:
 - Ran into issues with Gradle being unable to run & taking abnormally long to build
 - Programmed file reader that looks at each line and prints out the node it starts with and what node that first node travels to
Questions:
 - Are we going to have to guide Gradle through our files every time we want it to analyze the text file like how we had to do for it to properly use the file reader?

PHASE 2
DAY 3 - 12/5/23
Eva & Musa
7:00 PM - 9:00 PM
Progress:
 - Successfully had graph graphic appear when program is run
 - Counted out characteristics of graphs
    - Number of nodes: 26
    - Number of edges: 82
    - Maximum node degree: 6
    - Average node degree: ~3.12
 - Began implementing Dijkstra's algorithm as a method to determine shortest distance between two nodes
    - Copy/pasted psuedocode from Wikipedia page to be converted to actual code
        - Link: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
        - Link to website providing step-by-step for implementing Dijkstra's algorithm in java: https://www.baeldung.com/java-dijkstra

DAY 4 - 12/6/23
Eva
7:00 PM - 7:30 PM
Progress:
 - Recieved feedback from TAs about using Dijkstra's algorithm on our dataset without given real-world information on distance
 - Determined best course of action was to make the distance betweeen the nodes (the 'cost') a variable and make that essentially the distance

Eva & Musa
9:30 PM - 11:30 PM
Progress:
 - Approached completing implementing programming for Dijkstra's algorithm for our dataset

 DAY 5 - 12/7/23
 Eva & Musa
 7:00 PM - 10:30 PM
 Progress:
 - Nearly completed implementing programming for Dijkstra's algorithm; isn't displaying distances properly
      - Stuck on initial for loop for adjacent edges, suspect this part might be breaking it
 - Referred to YouTube video for implementation of algorithm (programmer programmed in Python, we translated to Java)
      - https://www.youtube.com/watch?v=XEb7_z5dG3c

DAY 6 - 12/8/23
Musa
2:10 PM - 4:00PM 
Progress:
   - Finished implementing Djikstra's Algorithm from the video we watched. 

DAY 7 - 12/10/23
Eva & Musa
7:00 PM - 9:00 PM
Progress:
 - Wrote Javadocs for all methods and classes in ReadFile file
 - Discussed ideas for implementing a path for the minimum distances between nodes (what you get from the Djikstra's algorithm method)
Questions:
 - Knowing the minumum distance from one node to another, how do you map a path for that distance using our dataset?

PHASE 3
DAY 8 - 12/12/23
Eva
7:00 PM - 9:00 PM, 10:30 PM - 12:00 AM
Progress:
 - Retooled Djikstra's Algorithm method
   - Added comments with referenced psuedocode to help navigate through the method more easily
   - Commented out second version of algorithm method
   - Changed type for source parameter for DjikstraAlgorithm method
   - Implemented remainder of method based on referred psuedocode; some errors remain preventing code from compiling

DAY 9 - 12/13/23
Eva & Musa
7:00 PM - 12:45 AM
Progress:
 - Finished Djikstra Algorithm method so that it compiles and displays graph with paths
 - Removed unconnected graph on right hand side to prevent code from breaking; may attempt to implement functionality with unconnected graphs after demo
 - Next: want to implement functionality that includes getting a path from a start to end node by following backwards from one node to its neighbor node all the way to 0
 - Attempted to copy/paste updated GraphDisplay file, but doing so broke the code, so we undid the change and are technically working with the old file

 Questions:
 - How are we supposed to make this generic if we need to change the path the text file comes from every time it switches computers in order for the program to properly locate and read the file?

DAY 9 - 12/13/23
Eva & Musa 
9:25 AM - 1:00 PM, 2:35 PM - 3:40 PM
Progress:
   - Got specific path from source node to target node.
   - Got Animation.java files to work (had to import a few tools from java.util).
   - Printed out the path from source node to target node clearly in the terminal. 
   - Allowed users to choose specifically the target node they want to find a path to. 

Musa 
6:30PM - 7:45PM
Progress: 
   - Fixed getPath() method in ReadFile.java to print out path from source node to chosen target node.
   - Allowed chosen path from source node to target node to animate itself with colors on a new graph display.
   Questions: How do we get the nodes to color themselves in order from the source node to the target node?

Eva & Musa - 7:00 PM - 12:00 AM
Progress:
   - Corrected for loop in getPath method to a while loop because it allows the program to run more efficiently
   - Updated graph display using file provided via Slack; now fully functional
   - Fixed graph animation to to be in proper order by fixing logic error in the getPath method
   - Implemented code to anticipate bad inputs (i.e assigning a value that doesn't exist in the dataset as a target node into the terminal)
   - Cleared out extra, unnecessary comments
   - Updated and added remaining javadocs

Reflection:

Eva's Reflection:
What went well was the initial construciton of the graph. This could be partially due to the fact that the dataset we used was vague enough with most of its characteristics that there wasn't much to implement. Nevertheless, it provided a good base that allowed us to eventually implement small deviations like a randomizer for the weight values, and animations that highlight the paths between nodes without things getting too complicated. It was definitely an effort of 'less is more' that paid off.

We struggled for a long time with Djikstra's algorithm as we used the wrong data structure at first. This made us confused about the issues we were running into as the program was constantly failing to detect the graph's value as anything other than null. We eventually discovered that we'd misassigned the types for the parameters, like making source parameter an integer instead of a string, and used arrays far more often than we needed to. This led to other issues like having a surplus of variables (at one point we assigned both the target node and its neighbors as their own variables), which made our code even more cluttered. We eventually took a step back and realized what the algorithm could and could not do, which helped us cut back on a lot of extra parts of the code, or retool them in ways that combined parts we had initially programmed separately. This allowed us to both get a better understanding of how the algorithm worked, and also made programming the functionality with the path much easier as we had a clearer idea of what aspects of the graph and dataset needed to interact with what. This caused the bulk of our confusion throughout this project, so we were extremely relieved to pass it. 

For future projects, I want to make it a priority to psuedocode as well as work on the layout longer beforehand. I feel like at least some of our issues could have been prevented with a bit more pre-implementation planning. I think the main reason we jumped right in was concerns over time, but we now know not to let that intimidate us and plan meticulously anyway as it could have positive consequences if we take the time to do it.

Musa's Reflection:
   We struggled a lot with Djikstra's algorithm too because of the way we were interpreting the psuedocode. While we eventually started understanding it towards the end of this project's timeline; if I'm being honest, I think just looking at it with a pair of fresh eyes each time went a long way towards understanding it. When I first looked at that psuedocode, I was extremely lost, but it kept getting more and more understandable each time I looked at it and each time we went to TA hours to figure it out. Also adding onto Eva's point about using the wrong data structure, we had the idea to stuff the distance and via hashtables in Djikstra's algorithm into an object ArrayList to get around the fact that we couldnt return two things in Java, but this ended up having problems down the line. From that, I've learned to stop trying to work against Java too much and instead try to find actual alternate solutions that don't lead us into a dead end.

   What worked well for this project was looking back at past assignments. I didn't know that the max method from assignment 1 was going to help me with printing out the maximum node degree, but I put it in and it worked like a charm. Talk about coming full-circle. I also think that knowing about different data structures helped us out with this assignment. For example, knowing that adding things to a set did it in random order helped us figure out how to make our set of nodes an arraylist instead in the Animation.java and ReadFile.java files. Overall, this took a minute, but I really like how our graph looks.  
   
   For future projects, I'd love to be more efficient and cleaner with the way we write our code. We've gotten comments from TAs that our code could be less messy (in a nice way), but this came with learning more stuff about how we were supposed to code 
   this project so we didn't have a lot of time to go back and make previous parts look more clean than they are. 