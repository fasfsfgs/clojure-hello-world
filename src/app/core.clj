(ns app.core
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.util.response :refer [resource-response]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.nested-params :refer [wrap-nested-params]]
            [ring.middleware.not-modified :refer [wrap-not-modified]]
            [camel-snake-kebab.core :as kebab]
            [clojure.java.jdbc :as db]
            [environ.core :refer [env]]))

(def sample (env :sample "sample-string-thing"))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (concat (for [kind ["camel" "snake" "kebab"]]
                   (format "<a href=\"/%s?input=%s\">%s %s</a><br />"
                           kind sample kind sample))
                 ["<hr /><ul>"]
                 (for [s (db/query (env :database-url "postgres://localhost:5432/testdb")
                                   ["select content from sayings"])]
                   (format "<li>%s</li>" (:content s)))
                 ["</ul>"])})

(defn record [input]
  (db/insert! (env :database-url "postgres://localhost:5432/testdb")
              :sayings {:content input}))

(defroutes appRoutes
  (GET "/camel" [input]
       (record input)
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body (kebab/->camelCase input)})
  (GET "/snake" {{input :input} :params}
       (record input)
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body (kebab/->snake_case input)})
  (GET "/kebab" {{input :input} :params}
       (record input)
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body (kebab/->kebab-case input)})
  (GET "/" []
       (resource-response "index.html" {:root "public"}))

  (route/resources "/")

  (route/not-found (slurp (io/resource "404.html"))))

(def app
  (-> appRoutes
      (wrap-keyword-params)
      (wrap-params)
      (wrap-not-modified)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port 5000)))]
    (jetty/run-jetty app {:port port :join? false})))
