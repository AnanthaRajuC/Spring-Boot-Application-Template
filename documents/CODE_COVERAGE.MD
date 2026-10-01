## Code Coverage

<details open="open">
	<ul>
		<li><a href="#jacoco">JaCoCo</a></li>
	</ul>
</details>

### JaCoCo

Code coverage is measured with [JaCoCo](https://www.jacoco.org/jacoco/). The Maven plugin attaches its agent while the tests run and writes the report in the `verify` phase:

```shell
./mvnw verify
```

Open the HTML report at:

**`target/site/jacoco/index.html`**

`target/site/jacoco/jacoco.xml` is the same report in XML, for tools such as Codecov or SonarCloud.

In GitHub Actions the HTML report is uploaded as the **jacoco-report** artifact of every build; download it from the run's summary page.
