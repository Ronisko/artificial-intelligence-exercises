(define
	(domain world-of-blocks)
	(:requirements :adl)
	(:predicates
		(on ?x ?y)
		(on-floor ?x)
		(clear ?x)
		(up ?x)
		(is-sth-up)
	)
	(:action opusc-na-podloge
		:parameters (?x)
		:precondition
		(and
			(up ?x)
		)
		:effect
		(and
			(not (is-sth-up))
			(not (up ?x))
			(on-floor ?x)
		)
	)
	(:action opusc-na-paczke
		:parameters (?x ?y)
		:precondition
		(and
			(clear ?x)
			(up ?y)
		)
		:effect
		(and
			(on ?y ?x)
			(not (is-sth-up))
			(not (on-floor ?y))
			(not (clear ?x))
			(not (up ?y))
		)
	)
	(:action podnies-z-podlogi
		:parameters (?x)
		:precondition
		(and
			(clear ?x)
			(on-floor ?x)
			(not (up ?x))
			(not (is-sth-up))
		)
		:effect
		(and
			(up ?x)
			(is-sth-up)
		)
	)
	(:action podnies-z-paczki
		:parameters (?x ?y)
		:precondition
		(and
			(on ?x ?y)
			(clear ?x)
			(not (clear ?y))
			(not (up ?x))
			(not (is-sth-up))
		)
		:effect
		(and
			(up ?x)
			(clear ?y)
			(not (on-floor ?x))
			(is-sth-up)
		)
	)
)