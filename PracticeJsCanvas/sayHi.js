document.getElementById('gsr').innerHTML = "<div style = 'width:200px; height:200px; margin:0 auto; padding:5px;'><canvas id = 'canvasArea' width = '200' height ='200' style = 'border:2px solid black'></canvas></div>"

canvas = document.getElementById('canvasArea');
context = canvas.getContext('2d');

var mText = 'Hi!'
var xPos = canvas.width/2;
var yPos = canvas.height/2;

context.font= '80pt Comic Sans MS';

context.fillStyle ='lime';
context.textAlign ='center';
context.textBaseline = 'middle';

context.fillText(mText, xPos, yPos);
