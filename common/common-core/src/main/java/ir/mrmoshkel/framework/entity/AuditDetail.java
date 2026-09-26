package ir.mrmoshkel.framework.entity;

import ir.mrmoshkel.user.entity.User;

import java.sql.Date;

public class AuditDetail {
    private Date createdDate;
    private Date modifiedDate;
    private User createdBy;
    private User lastModifiedBy;
}
