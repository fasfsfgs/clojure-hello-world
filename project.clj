(defproject clojure-hello-world "0.1.0-SNAPSHOT"

  :description "learning the basics of clojure"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.4.0"]]

  :plugins [[lein-ring "0.9.6"]]

  :ring {:handler app.core/app})
