(define
	(domain sokoban)
	(:requirements :adl)
	(:predicates
		(paczka ?x)
		(pionowo ?x ?y)
		(poziomo ?x ?y)
		(cel ?x)
		(robot ?x)
	)
	(:action idz
		:parameters (?x ?y)
		:precondition
		(and
			(robot ?x)
			(not (paczka ?y))
			(or (pionowo ?x ?y) (poziomo ?x ?y))
		)
		:effect
		(and
			(not (robot ?x))
			(robot ?y)
		)
	)
	(:action pchaj
		:parameters (?x ?y ?z)
		:precondition
		(and
			(or (and (pionowo ?x ?y) (pionowo ?y ?z)) (and (poziomo ?x ?y) (poziomo ?y ?z)))
			(robot ?x)
			(paczka ?y)
			(not (paczka ?z))
			(not (= ?x ?z))
		)
		:effect
		(and
			(robot ?y)
			(not (robot ?x))
			(paczka ?z)
			(not (paczka ?y))
		)
	)
)