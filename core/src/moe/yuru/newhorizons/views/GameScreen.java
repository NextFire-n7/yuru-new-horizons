package moe.yuru.newhorizons.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import moe.yuru.newhorizons.YuruNewHorizons;
import moe.yuru.newhorizons.models.GameSolo;
import moe.yuru.newhorizons.models.Town;

/**
 * Game {@link Screen}. This is where the player will spend most of his time.
 */
public class GameScreen implements Screen {

    private YuruNewHorizons game;
    private GameStage gameStage;
    private MapStage mapStage;

    private InputMultiplexer inputMultiplexer;

    /**
     * @param game the game instance
     */
    public GameScreen(YuruNewHorizons game) {
        this.game = game;

        game.setModel(new GameSolo(new Town("east-a1"))); // TODO: for debug purpose heh
        game.setGameScreen(this);

        gameStage = new GameStage(game);
        mapStage = new MapStage(game);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gameStage);
        inputMultiplexer.addProcessor(mapStage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        game.getFpslogger().log();
        game.getCamera().update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getModel().getTown().updateBalance(delta);
        game.getModel().updateEnemy(delta);

        gameStage.act(delta);
        gameStage.draw();

        mapStage.act(delta);
        mapStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }

}
