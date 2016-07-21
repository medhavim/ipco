
INSERT INTO `ipco`.`user_type` (`user_type_id`, `user_type_desc`, `created_ts`) VALUES ('1', 'Admin', sysdate());
INSERT INTO `ipco`.`user_type` (`user_type_id`, `user_type_desc`, `created_ts`) VALUES ('2', 'User', sysdate());

INSERT INTO `ipco`.`topic_type` (`type_id`, `type_desc`, `created_ts`) VALUES ('1', 'Basic', sysdate());
INSERT INTO `ipco`.`topic_type` (`type_id`, `type_desc`, `created_ts`) VALUES ('2', 'Custom', sysdate());

INSERT INTO `ipco`.`user` (`user_id`, `email`, `first_name`, `last_name`, `created_ts`, `credential_id`, `user_type_id`) VALUES ('1', 'iam@adm.in', 'Admin', 'admin', sysdate(), '1', '1');