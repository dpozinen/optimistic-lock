/*
 * Copyright by onstructive GmbH 2020 - 2021. All rights reserved.
 *
 * onstructive GmbH
 * Josefstrasse 92
 * 8005 Zürich
 * Switzerland
 *
 * Unauthorized copying of this file via any medium is strictly prohibited.
 */
package optimistic.lock.answeroption;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optimistic.lock.BaseEntity;

@Setter
@Getter
@Entity
@ToString
@Table(name = "answer_option")
public class AnswerOption extends BaseEntity {

}
