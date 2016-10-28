(define (problem p2)
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
			(on-floor a) (on-floor b) (on-floor c) (on-floor d) (on-floor e)
		)
	)
)