(ns rtracer.core
  (:gen-class)
  (:import java.awt.image.BufferedImage java.awt.Color)  
  (:import javax.imageio.ImageIO)
  (:import java.io.File)
  )

(def dimensions {:width 320 :height 200})

(defn imgwidth [] (dimensions :width))
(defn imgheight [] (dimensions :height))

(defn make-image-buffer [width height] (BufferedImage. width height BufferedImage/TYPE_INT_ARGB))

(def image (make-image-buffer (imgwidth) (imgheight)))

(defn save-image [image filename]
  (ImageIO/write image "png" (File. filename))
)

(defprotocol PixelOps 
  (toInt [x])
  (sayHello [x])  
)

(defrecord Pixel [red green blue alpha] PixelOps
  (toInt [this] (bit-or (bit-shift-left alpha 24) (bit-shift-left red 16) (bit-shift-left green 8 ) blue))  
  (sayHello [this] (println "hello " red green blue alpha))
)

(defn makerow [width ]
  (map (fn [_] (ref (Pixel. 0 0 0 0))) (range width))  
)

(def imagedata (vec 
  (map (fn [_]  
        (vec 
          (makerow (imgwidth))
        )
    ) (range (imgheight))
  )
))

(defn getpixelref [x y]
  ((imagedata y) x) 
)

(defn setpixel [x y r g b a]
  (dosync 
    (alter ( (imagedata y) x ) assoc :red r :green g :blue b :alpha a )
  )
)

(defn render []
  (doseq [[x y] (for [y (range (imgheight)) x (range (imgwidth)) :while true] [x y])]
    (let [pixval (toInt @(getpixelref x y))] 
        (. image setRGB x y pixval)
;        (if (> pixval 0) (println "setting " x  y pixval))
    )  
  )  
)

(defn makeimage[] 
  (time ( let [xscale (/ 255 (imgwidth)) yscale (/ 255 (imgwidth))]
    (doseq [[x y] (for [x (range (imgwidth)) y (reverse (range (imgheight))) :while true] [x y])]
      (setpixel x y (Math/round (float (* xscale x))) 64 (Math/round (float (* yscale y))) 255)
    )
    ))
  (time (render))
  (time (save-image image "foo.png"))
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
)
