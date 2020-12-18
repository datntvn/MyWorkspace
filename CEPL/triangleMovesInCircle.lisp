;; NOTE: Success in moving triangle in circle
;; TODO:: Need to findout more about these thing: v!, vec2, vec3, in order to compact the source code

  ;; I'm a newbie to both Common Lisp, and, newbie in OpenGL also
(in-package :cepl.examples)

(defparameter *array* nil) ; I have to define some global variable, to use within function step-demo
(defparameter *sinloop* nil); I don't know why function step-demo does not recognize local variable in run-time
(defparameter *cosloop* nil)

(defparameter *myarray* nil)
(defparameter *stream* nil)
(defparameter *running* nil) 
(defparameter *loop-pos* 0.0) ; This variable to be increment by 1 every loop

(defparameter *onevert* nil) ; this is x for 1st vertex
(defparameter *twovert* nil) ; this is x for 2nd vertex
(defparameter *threevert* nil) ; this is x for 3rd vertex

(defparameter *onevert-y* nil) ; this is y for 1st vertex
(defparameter *twovert-y* nil) ; this is y for 2nd vertex
(defparameter *threevert-y* nil) ; this is y for 3rd vertex

; Initialize *myarray* with a predefine data so that we can reuse, and increasea value for each vertex
(setf *myarray* (list (list (v!  0.5 -0.36 0) (v! 0 1 0 1)) 
                                    (list (v!    0   0.5 0) (v! 1 0 0 1))
                                    (list (v! -0.5 -0.36 0) (v! 0 0 1 1))))


(defstruct-g pos-col
  (position :vec3 :accessor pos)
  (color :vec4 :accessor col))

(defun-g tri-vert ((vert pos-col))
  (values (v! (pos vert) 1.0)
          (col vert)))

(defun-g tri-frag ((color :vec4))
  color)

(defpipeline-g prog-1 ()
  (tri-vert pos-col)
  (tri-frag :vec4))

(defun step-demo ()
  (setf *loop-pos* (+ 0.001 *loop-pos*)) ; increase value for *loop-pos*

  ;; BEGIN - change the position for triangle
  (setf *sinloop* (sin *loop-pos*)) ; calculate the sin value of *loop-pos*
  (setf *cosloop* (cos *loop-pos*))

  (setf *onevert* (+ *sinloop* (aref (first (first *myarray*)) 0)) ) ; calculate the new value for 1st vertice
  (setf *twovert* (+ *sinloop* (aref (first (second *myarray*)) 0))) ; similarly, calculate the new value for 2nd vertice
  (setf *threevert* (+ *sinloop* (aref (first (third *myarray*)) 0)) ) ; similarly, calculate the new value for 3rd vertice

  (setf *onevert-y* (+ *cosloop* (aref (first (first *myarray*)) 1)) ) ; calculate the new value for 1st vertice
  (setf *twovert-y* (+ *cosloop* (aref (first (second *myarray*)) 1))) ; similarly, calculate the new value for 2nd vertice
  (setf *threevert-y* (+ *cosloop* (aref (first (third *myarray*)) 1)) ) ; similarly, calculate the new value for 3rd vertice

  ; (format t "~,2f" *onevert-y*)

  (push-g `((#(,*onevert* ,*onevert-y* 0.0) #(0.0 1.0 0.0 1.0)) ; then, use push-g to push new data into GPU
  (#(,*twovert* ,*twovert-y* 0.0) #(1.0 0.0 0.0 1.0))
  (#(,*threevert* ,*threevert-y* 0.0) #(0.0 0.0 1.0 1.0))) *array*)
  ;; END - change the position for triangle

  (step-host)
  (update-repl-link)
  (clear)
  (map-g #'prog-1 *stream*)
  (swap))

(defun run-loop ()
  (setf *running* t
        *array* (make-gpu-array *myarray* 
                                :element-type 'pos-col)
        *stream* (make-buffer-stream *array*))
  (loop :while (and *running* (not (shutting-down-p))) :do
     (continuable (step-demo))))

(defun stop-loop ()
  (setf *running* nil))
