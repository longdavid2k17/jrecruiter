package com.dkantoch.jrecruiter.payload.response;

public class VersionResponse
{
    private String version;

    public VersionResponse(String version)
    {
        this.version = version;
    }

    public VersionResponse()
    {

    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "VersionResponse{" +
                "version='" + version + '\'' +
                '}';
    }
}
