class VoiceTileService : TileService() {
    override fun onClick() {
        super.onClick()
        val tile = qsTile
        val isActive = (tile.state == Tile.STATE_ACTIVE)
        
        if (isActive) {
            tile.state = Tile.STATE_INACTIVE
            // Send instruction to AccessibilityService to STOP listening
        } else {
            tile.state = Tile.STATE_ACTIVE
            // Send instruction to AccessibilityService to START listening
        }
        tile.updateTile()
    }
}