package io.github.anantharajuc.sbat.core_backend.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Branding shown on the home page, bound from the {@code sbat.site.*} properties.
 */
@ConfigurationProperties("sbat.site")
public record SiteProperties(String logo, String initials, String title, String description)
{
	public SiteSettings toSiteSettings()
	{
		return new SiteSettings(logo, initials, title, description);
	}
}
