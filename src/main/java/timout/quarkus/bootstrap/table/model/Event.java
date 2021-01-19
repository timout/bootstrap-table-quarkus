package timout.quarkus.bootstrap.table.model;

sealed public interface Event permits DeleteEvent, RandomDeleteEvent, GenerateEvent{
}

