# figwheel01

cljs.core.async

html5 canvas

## Overview

![figwheel01.gif](https://raw.githubusercontent.com/griffio/figwheel01/master/figwheel01.gif)

~~~
:dependencies [[org.clojure/clojure "1.8.0"]
               [org.clojure/clojurescript "1.8.40"]]
~~~
To get an interactive development environment run:

~~~
lein figwheel
~~~

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

To clean all compiled files:
~~~
lein clean
~~~

To create a production build run:
~~~
lein do clean, cljsbuild once min
~~~

And open your browser in `resources/public/index.html`. You will not get live reloading, nor a REPL.

## License

Copyright © 2016

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
