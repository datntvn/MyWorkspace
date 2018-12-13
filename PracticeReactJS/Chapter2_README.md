Because the book is way too old interm of compatible to latest version of dependencies, we have to make below modification, you can skipp the part that is marked within BEGIN - END which I noted for my own.

# Update 1
Chapter 2 need to add below packages:  
  
react-bootstrap: npm install react-bootstrap --save-dev  
react-router: npm install react-router --save-dev  
lodash: npm install lodash --save-dev  
reflux: npm install reflux --save-dev  
superagent: npm install superagent --save-dev  
react-router-bootstrap: npm install react-router-bootstrap --save-dev  
  
# Update 2
You should use nvm to be easier in finding which version of node work for you (based on the list of dependencies that the book sample required)

Having error when execute "npm install" due to nodejs & nvm in 2018 is not compatible with the version required by the book
  
Refer to this link: http://dev.topheman.com/install-nvm-with-homebrew-to-use-multiple-versions-of-node-and-iojs-easily/

All version of node I had installed:  v6.7.0,v6.9.5,v6.11.3,v7.0.0,v7.10.0
and I tested, and command "npm install" works for 7.0.0, and 6.11.3

# BEGIN - skip this part
..
Below are notes from installing nvm via brew, I kept the information here for later reference in case I want to completely remove this installation manually

    You should create NVM's working directory if it doesn't exist:

      mkdir ~/.nvm

    Add the following to ~/.bash_profile or your desired shell
configuration file:

      export NVM_DIR="$HOME/.nvm"
      . "/usr/local/opt/nvm/nvm.sh"

    You can set $NVM_DIR to any location, but leaving it unchanged from
    /usr/local/opt/nvm will destroy any nvm-installed Node installations
    upon upgrade/reinstall.

    Type `nvm help` for further information.
  
# END - skip this part
-------------
In 2016, the book ReactJS blueprints was released, so, I guess the version of nodejs could be: Node.js 7.0.0
  Now using node v7.0.0 (npm v3.10.8)
  
  
---------------
## Long story short, in order to avoid below errors you need to  
##   change version of "jsxstyle" to "0.0.22" within file package.json

# BEGIN - skip this part
Below is the detail of error that I faced along with tried that I made.

command "npm install" continue to product error  
Error 1> xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
  
It seems like "Indamix" at https://stackoverflow.com/questions/17980759/xcode-select-active-developer-directory-error/17980786#17980786 has solved the problem

  
Error 2> gyp ERR! stack Error: `gyp` failed with exit code: 1
  
I refer to https://github.com/kelektiv/node.bcrypt.js/issues/297
  
After following "hernanifernande" 's guide, as below
  rm -rf ~/.node-gyp/
  rm -r node_modules/.bin/;
  rm -r build/
  npm install bcrypt


 I have next error 

Error 3> it looks like the error was produced after hit "node-gyp rebuild", error details is as below:

make: *** [Release/obj.target/contextify/src/contextify.o] Error 1
gyp ERR! build error 
gyp ERR! stack Error: `make` failed with exit code: 2
gyp ERR! stack     at ChildProcess.onExit (/Users/<home directory>/.nvm/versions/node/v7.0.0/lib/node_modules/npm/node_modules/node-gyp/lib/build.js:276:23)
gyp ERR! stack     at emitTwo (events.js:106:13)
gyp ERR! stack     at ChildProcess.emit (events.js:191:7)
gyp ERR! stack     at Process.ChildProcess._handle.onexit (internal/child_process.js:215:12)
gyp ERR! System Darwin 18.0.0

DatNT note: discover error when running adding this "jsxstyle" into package.json, then run "npm install" it will execute "node-gyp rebuild" that cause troubles


After digging the error messages a little bit more, it looks like, the error is there because
   contextify@0.1.15 install: `node-gyp rebuild`
   The same bug is reported at: https://github.com/brianmcd/contextify/issues/205
  

# WOW, this fix for me "jsxstyle": "0.0.22"
i.e changing version of jsxstyle to 0.0.22. All other solutions failed (even downgrade of upgrade version of nodejs does not work)
# END - skip this part

OK, then all other packages can be installed alright.

