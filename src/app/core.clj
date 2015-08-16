(ns app.core
  (:use ring.util.response
        ring.middleware.resource
        ring.middleware.content-type
        ring.middleware.params
        ring.middleware.not-modified))

(defn handler [request]
  (-> (response "dynamic response")
      (content-type "text/html")
      (charset "utf-8")))

(def app
  (-> handler
      wrap-params
      (wrap-resource "public")
      wrap-content-type
      wrap-not-modified))
