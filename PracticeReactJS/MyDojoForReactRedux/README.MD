# Explain about file MyDiagramForReactReduxUnderstanding

This is my creation to  help me quickly imagine about all the things which help react & redux collaborate with each other  

# Usage

The co-related source code is this link: https://www.youtube.com/watch?v=qrsle5quS7A&list=PL55RiY5tL51rrC3sh8qLiYHqUV3twEYU_
  
I had sketched this diagram in a way that is very efficient to quickly remember all the CRITICAL things that you need a combo of react-redux up and running
It systematically summarized critical details
Other things that I did not mentioned within the document just related to programming technique which you can fill into later
  
From my opinion, redux's action is not an action at all, it is like a data structure defined by JSON format. Within this json, we have a key-value pair for: action-type: <value of this action>:
	e.g: "SET_NAME": "Mathew Smith"
  
To me, this should have been called "actionData" or "actionDetail" or something else...
  
Another thing is that, the function "dispatch" is actually the THING which should have been called action. Function "dispatch" will send the action (i.e the actionData/actionDetail) to the reducer
  
One last thing, reducers are functions that handle the actions triggered by "dispatch". And, therefore, reducers should have been called ActionHandler which is a lot easier to understand.