package net.byteflux.libby.transitive;

import net.byteflux.libby.Library;
import org.eclipse.aether.repository.RemoteRepository;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Simple immutable data-class for holding {@code groupId}, {@code artifactId}.
 *
 * @see TransitiveDependencyHelper#findCompileDependencies(String, String, String, RemoteRepository...) 
 */
public class ExcludedLibrary {

    /**
     * Maven group ID
     */
    private final String groupId;
    /**
     * Maven artifact ID
     */
    private final String artifactId;

    /**
     * Creates a new {@link ExcludedLibrary}
     *
     * @param groupId    Maven group ID
     * @param artifactId Maven artifact ID
     */
    private ExcludedLibrary(String groupId, String artifactId) {
        this.groupId = requireNonNull(groupId, "groupId").replace("{}", ".");
        this.artifactId = requireNonNull(artifactId, "artifactId");
    }

    /**
     * Creates a new {@link ExcludedLibrary} from raw {@code groupId}, {@code artifactId}
     *
     * @param groupId Maven group ID
     * @param artifactId Maven artifact ID
     * @return new {@link ExcludedLibrary} instance
     *
     * @see #similar(Library)
     */
    public static ExcludedLibrary of(String groupId, String artifactId) {
        return new ExcludedLibrary(groupId, artifactId);
    }

    /**
     * Gets the Maven group ID for this excluded library
     *
     * @return Maven group ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Gets the Maven artifact ID for this excluded library
     *
     * @return Maven artifact ID
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Returns similarity of {@link Library} and this class.
     * <p>
     * This method checks equality of {@code groupId}, {@code artifactId}.
     *
     * @return {@code} if {@code groupId}, {@code artifactId} are equals with this class.
     * @see TransitiveDependencyHelper#findCompileDependencies(String, String, String, RemoteRepository...)
     */
    public boolean similar(Library library) {
        return Objects.equals(groupId, library.getGroupId()) && Objects.equals(artifactId, library.getArtifactId());
    }
}
