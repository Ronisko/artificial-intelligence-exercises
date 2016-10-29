(define
	(domain hanoi)
	(:requirements :adl)
	(:types disk rod)
	(:constants x y z - rod) 
	(:predicates
		(on-rod ?a - disk ?b - rod)
		(lt ?a ?b - disk)
		(less ?a ?b - disk)
	)
	(:derived (less ?a ?b - disk)
	(or
		(lt ?a ?b)
		(exists (?c - disk) (and (lt ?a ?c) (less ?c ?b)))
	)
	)	
	(:action przesun-na-pusty
		:parameters (?a ?b - rod ?c - disk)
		:precondition
		(and
			(on-rod ?c ?a)
			(not (exists (?d - disk) (and (on-rod ?d ?a) (less ?d ?c))))
			(not (exists (?d - disk) (on-rod ?d ?b)))
		)
		:effect
		(and
			(on-rod ?c ?b)
			(not (on-rod ?c ?a))
		)
	)
	(:action przesun
		:parameters (?a ?b - rod ?c ?d - disk)
		:precondition
		(and
			(less ?c ?d)
			(not (exists (?e - disk) (and (on-rod ?e ?a) (less ?e ?c))))
			(not (exists (?e - disk) (and (on-rod ?e ?b) (less ?e ?d))))
			(on-rod ?c ?a)
			(on-rod ?d ?b)
		)
		:effect
		(and
			(on-rod ?c ?b)
			(not (on-rod ?c ?a))
		)
	)
)
