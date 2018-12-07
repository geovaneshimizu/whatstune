package io.geovaneshimizu.whatstune.music;

interface PlaylistRepository {

    Playlist findByCategory(Category category);
}
