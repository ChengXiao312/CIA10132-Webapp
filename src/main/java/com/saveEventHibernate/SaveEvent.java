package com.saveEventHibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "save_event")
public class SaveEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EventId eventId;

    public SaveEvent() {
    }

    public SaveEvent(Integer memberNo, Integer eventNo) {
        this.eventId = new EventId(memberNo, eventNo);
    }

    public EventId getEventId() {
        return eventId;
    }

    public void setEventId(EventId eventId) {
        this.eventId = eventId;
    }

    @Embeddable
    public static class EventId implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name = "member_no")
        private Integer memberNo;

        @Column(name = "event_no")
        private Integer eventNo;

        public EventId() {
        }

        public EventId(Integer memberNo, Integer eventNo) {
            this.memberNo = memberNo;
            this.eventNo = eventNo;
        }

        public Integer getMemberNo() {
            return memberNo;
        }

        public void setMemberNo(Integer memberNo) {
            this.memberNo = memberNo;
        }

        public Integer getEventNo() {
            return eventNo;
        }

        public void setEventNo(Integer eventNo) {
            this.eventNo = eventNo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EventId eventId = (EventId) o;

            if (!memberNo.equals(eventId.memberNo)) return false;
            return eventNo.equals(eventId.eventNo);
        }

        @Override
        public int hashCode() {
            int result = memberNo.hashCode();
            result = 31 * result + eventNo.hashCode();
            return result;
        }
    }
}
