(defproject clojure-hello-world "1.0.0-SNAPSHOT"

  :description "learning the basics of clojure"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [camel-snake-kebab "0.3.2"]
                 [environ "1.0.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [postgresql "9.3-1102.jdbc41"]]

  :min-lein-version "2.0.0"

  :plugins [[environ/environ.lein "0.3.1"]
            [lein-ring "0.8.11"]]

  :ring {:handler app.core/app :port 5000}

  :hooks [environ.leiningen.hooks]

  :uberjar-name "clojure-hello-world-standalone.jar"

  :profiles {:production {:env {:production true}}})
