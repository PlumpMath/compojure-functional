# compojure-func

A Clojure library for functional testing Compojure apps.

## Usage

```clojure
(ns myapp.test
  (:require [myapp.web :as web]
            [compojure-func :as f]))

(def app web/handler)

(deftest home-page-test

  (testing "Should return HTTP success"
    (f/with-app app
	  (f/assert-response :get "/" 200)))

  (testing "Homepage should contain \"Hello world\""
    (f/with-app app
	  (f/assert-contains :get "/" "Hello world"))))

(deftest private-page-test
  (testing "Should return HTTP not found when not logged in"
    (f/with-app app
	  (f/assert-response :get "/private" 404))))

```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
