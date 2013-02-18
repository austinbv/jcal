package calendar.model;

import java.util.Collection;

public class Event {
  private String kind;
  private String etag;
  private String summary;
  private String description;
  private String updated;
  private String timeZone;
  private String accessRole;
  private Collection<Item> items;

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getEtag() {
    return etag;
  }

  public void setEtag(String etag) {
    this.etag = etag;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUpdated() {
    return updated;
  }

  public void setUpdated(String updated) {
    this.updated = updated;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public String getAccessRole() {
    return accessRole;
  }

  public void setAccessRole(String accessRole) {
    this.accessRole = accessRole;
  }

  public Collection<Item> getItems() {
    return items;
  }

  public void setItems(Collection<Item> items) {
    this.items = items;
  }
}
