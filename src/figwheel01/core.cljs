(ns ^:figwheel-always figwheel01.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)

(println "Page loading...")

(defonce app-state (atom 0))                                ;; define your app data so that it doesn't get over-written on reload

(add-watch app-state :console #(-> %4 clj->js js/console.log))

(def ctx2d (.getContext (dom/getElement "js_canvas") "2d"))

(defn on-js-reload []
  (swap! app-state inc))                                    ;; optionally touch app-state to call add-watch[ers]

(defn listens [el type]
  (let [out (chan)]
    (events/listen el type
                   (fn [e] (put! out e)))
    out))

(defn init-canvas! [h w]
  (let [canvas (dom/getElement "js_canvas")]
    (set! (.-height canvas) h)
    (set! (.-width canvas) w)))

(defn draw2d! []
  (set! (.-fillStyle ctx2d) "#FA6900")
  (set! (.-shadowOffsetX ctx2d) @app-state)
  (set! (.-shadowOffsetY ctx2d) @app-state)
  (set! (.-shadowBlur ctx2d) 0)
  (set! (.-shadowColor ctx2d) "rgba(204, 204, 204, 0.5)")
  (.fillRect ctx2d 0 0 125 150))

(defn animate! []
  (.clearRect ctx2d (.-shadowOffsetX ctx2d) (.-shadowOffsetY ctx2d) 125 150)
  (set! (.-shadowOffsetX ctx2d) @app-state)
  (set! (.-shadowOffsetY ctx2d) @app-state)
  (.fillRect ctx2d 0 0 125 150))

(let [sliding (listens (dom/getElement "js_slider") "change")]
  (go (while true
        (let [value (-> (<! sliding) (.-target) (.-value))]
          (reset! app-state value)))))

(add-watch app-state :animate #(-> (js/requestAnimationFrame animate!)))

(init-canvas! 600 800)
(draw2d!)
