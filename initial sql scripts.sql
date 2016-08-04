
INSERT INTO `ipco`.`user_type` (`user_type_id`, `user_type_desc`, `created_ts`) VALUES ('1', 'Admin', sysdate());
INSERT INTO `ipco`.`user_type` (`user_type_id`, `user_type_desc`, `created_ts`) VALUES ('2', 'User', sysdate());

INSERT INTO `ipco`.`topic_type` (`type_id`, `type_desc`, `created_ts`) VALUES ('1', 'Basic', sysdate());
INSERT INTO `ipco`.`topic_type` (`type_id`, `type_desc`, `created_ts`) VALUES ('2', 'Custom', sysdate());

INSERT INTO `ipco`.`user` (`user_id`, `email`, `first_name`, `last_name`, `created_ts`, `credential_id`, `user_type_id`) VALUES ('1', 'iam@adm.in', 'Admin', 'admin', sysdate(), '1', '1');


INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('1', 'Multiple Choice Question', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('2', 'Yes or No', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('3', 'Image Description', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('4', 'Image Multiple Choice Question', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('5', 'Image Yes or No', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('6', 'Video Description', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('7', 'Video Multiple Choice Question', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('8', 'Video Yes or No', sysdate());
INSERT INTO `ipco`.`activity_template` (`activity_template_id`, `activity_template_name`, `created_ts`) VALUES ('9', 'Information', sysdate());

INSERT INTO `ipco`.`instance_type` (`instance_type_id`, `instance_type_desc`, `created_ts`) VALUES ('1', 'Basic', sysdate());
INSERT INTO `ipco`.`instance_type` (`instance_type_id`, `instance_type_desc`, `created_ts`) VALUES ('2', 'Custom', sysdate());

INSERT INTO `ipco`.`status` (`status_id`, `status_desc`, `created_ts`) VALUES ('1', 'Not Started', sysdate());
INSERT INTO `ipco`.`status` (`status_id`, `status_desc`, `created_ts`) VALUES ('2', 'Incomplete', sysdate());
INSERT INTO `ipco`.`status` (`status_id`, `status_desc`, `created_ts`) VALUES ('3', 'Complete', sysdate());


