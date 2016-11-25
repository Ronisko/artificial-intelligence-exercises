(define (problem p1)
	(:domain world-of-blocks)
	(:objects a b c d e)
	(:init
		(clear c)
		(on c b)
		(on b a)
		(on-floor a)
		(clear e)
		(on e d)
		(on-floor d)
	)
	(:goal
		(and
			(on d b)
			(or (on b e) (on b c) (on b a))
		)
	)
)