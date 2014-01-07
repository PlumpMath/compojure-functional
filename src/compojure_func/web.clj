(ns compojure-func.web
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes app
  (GET "/" [] "Hello world")
  (route/not-found "<h1>Page not found</h1>"))
