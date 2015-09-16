(defproject clojure-hello-world "1.0.0-SNAPSHOT"

  :description "learning the basics of clojure"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.4.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [postgresql "9.3-1102.jdbc41"]
                 [environ "1.0.1"]]

  :min-lein-version "2.4.0"

  :plugins [[lein-environ "1.0.1"]
            [lein-ring "0.9.6"]]

  :ring {:handler app.core/app :port 5000}

  :uberjar-name "clojure-hello-world-standalone.jar"

  :profiles {:production {:env {:production true}}})
