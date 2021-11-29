/*
 * Copyright by onstructive GmbH 2020 - 2020-2020. All rights reserved.
 *
 * onstructive GmbH
 * Josefstrasse 92
 * 8005 Zürich
 * Switzerland
 *
 * Unauthorized copying of this file via any medium is strictly prohibited.
 */
package optimistic.lock;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {

  /** Unique identifier */
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(updatable = false, nullable = false, length = 36)
  @Type(type = "optimistic.lock.extra.UuidUserType")
  private UUID id;

  @Version
  @Column(nullable = false)
  private Integer version;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
