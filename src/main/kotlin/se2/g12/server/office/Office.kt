package se2.g12.server.office

import jakarta.persistence.*
import se2.g12.server.counter.Counter

@Entity

class Office() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var OfficeId:Long?=null
    @OneToMany
    private var listOfCounter= listOf<Counter>()
}
