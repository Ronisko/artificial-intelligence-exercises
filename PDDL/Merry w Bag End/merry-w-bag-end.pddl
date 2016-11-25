(define
	(domain merry-w-bag-end)
	(:requirements :adl)
	(:types location color block place)
	(:constants A B C D E F G H I - location a1 a2 a3 b1 b2 b3 c1 c2 c3 - place)
	(:predicates
		(got-one ?c - color)
		(got-two ?c - color)
		(got-three ?c - color)
		(man ?l - location)
		(lie-one ?c - color ?l - location)
		(lie-two ?c - color ?l - location)
		(way ?c - color ?l1 ?l2 - location) 
		(on ?b - blocks ?p - place)
		(empty ?p - place)
		(connect ?p ?p1 - place)
		
	)
	(:action wez
		:parameters (?c - color)
		:precondition
		(and
			(exists (?l - location) (and (man ?l) (or (lie-one ?c ?l) (lie-two ?c ?l) ) ) ) 
		)
		:effect
		(and
			(forall (?l - location) (when (and (man ?l) (lie-one ?c ?l) ) (not (lie-one ?c ?l)) ) )
			(forall (?l - location) (when (and (man ?l) (lie-two ?c ?l) ) (and (not (lie-two ?c ?l)) (lie-one ?c ?l)) ) )
			(when (got-two ?c) (got-three ?c))
			(when (got-one ?c) (got-two ?c))
			(when (not (got-one ?c)) (got-one ?c))
			
		)
	)
	(:action idz
		:parameters (?l2 - location)
		:precondition
		(and
			(exists (?c - color) (exists (?l1 - location) (and (man ?l1) (way ?c ?l1 ?l2) (or (got-one ?c) (got-two ?c) (got-three ?c) ) ) ) )
		)
		:effect
		(and
			(forall (?c - color) (forall (?l1 - location) (when (and (man ?l1) (way ?c ?l1 ?l2) (got-one ?c)) (not (got-one ?c) ) )) )
			(forall (?c - color) (forall (?l1 - location) (when (and (man ?l1) (way ?c ?l1 ?l2) (got-two ?c)) (and (not (got-two ?c)) (got-one ?c)) )) )
			(forall (?c - color) (forall (?l1 - location) (when (and (man ?l1) (way ?c ?l1 ?l2) (got-three ?c)) (and (not (got-three ?c)) (got-two ?c)) )) )
			
			
			(forall (?l1 - location) (not (man ?l1)) )
			(man ?l2)
		)
	)
	(:action przesun
		:parameters (?b - block)
		:precondition
		(and
			(man A)
			(exists (?p1 ?p2 - place) (and (empty ?p1) (connect ?p1 ?p2) (on ?b ?p2) ) )
		)
		:effect
		(and
			(forall (?p1 ?p2 - place) (when (and (empty ?p1) (connect ?p1 ?p2) (on ?b ?p2) ) (and (not (empty ?p1)) (empty ?p2) (not (on ?b ?p2)) (on ?b ?p1))) )
		)
	)
)
