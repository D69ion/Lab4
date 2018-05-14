package com.company.io;

import com.company.humanResources.EmployeeGroup;

public interface FileSource extends Source<EmployeeGroup> {
    void setPath(String path);
    String getPath();
}
