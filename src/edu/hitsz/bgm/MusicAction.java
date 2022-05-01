package edu.hitsz.bgm;

import edu.hitsz.application.MusicThread;

import java.util.concurrent.*;

/**
 * @author 谢岸峰
 */
public class MusicAction {

    private boolean gameBgmFlag = false;
    private boolean bossBgmFlag = false;

    /**
     * 线程池，播放背景音乐
     */
    private final ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            7,
            7,
            1000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(7),
            r -> new Thread(r)
    );

    /**
     * 线程池，用于单独循环boss敌机出现的背景音乐
     */
    private final ScheduledExecutorService executorServiceBossBgm = new ScheduledThreadPoolExecutor(1);




    /**
     * 线程池，用于单独循环游戏背景音乐
     */
    private final ScheduledExecutorService executorServiceGameBgm = new ScheduledThreadPoolExecutor(1);

    /**
     * boss敌机出现的bgm
     */
    private final MusicThread musicThreadBossBgm = new MusicThread("src/videos/bgm_boss.wav");
    /**
     * 英雄机射出子弹的bgm
     */
    private final MusicThread musicThreadShootBgm = new MusicThread("src/videos/bullet.wav");
    /**
     * 英雄机子弹击中敌机后的bgm
     */
    private final MusicThread musicThreadShootHitBgm = new MusicThread("src/videos/bullet_hit.wav");

    /**
     * 游戏结束的bgm
     */
    private final MusicThread musicThreadGameOverBgm = new MusicThread("src/videos/game_over.wav");

    /**
     * 英雄机获取炸弹道具的bgm
     */
    private final MusicThread musicThreadBombExplosionBgm = new MusicThread("src/videos/bomb_explosion.wav");

    /**
     * 英雄机获取火力道具的bgm
     */
    private final MusicThread musicGetSupplyBgm = new MusicThread("src/videos/get_supply.wav");
    /**
     * 游戏背景音乐的bgm
     */
    MusicThread musicThreadGameBgm = new MusicThread("src/videos/bgm.wav");

    public void gameBgm(){
        musicThreadGameBgm.setFlag(gameBgmFlag);
        executorServiceGameBgm.scheduleWithFixedDelay(musicThreadGameBgm,40,40,TimeUnit.MILLISECONDS);
    }

    public void shootBgm(){
        musicThreadShootBgm.setFlag(true);
        executorService.execute(musicThreadShootBgm);
    }

    public void shootHitBgm(){
        musicThreadShootHitBgm.setFlag(true);
        executorService.execute(musicThreadShootHitBgm);
    }

    public void gameOverBgm(){
        musicThreadGameOverBgm.setFlag(true);
        executorService.execute(musicThreadGameOverBgm);
    }

    public void bombExplosion(){
        musicThreadBombExplosionBgm.setFlag(true);
        executorService.execute(musicThreadBombExplosionBgm);
    }

    public void getSupply(){
        musicGetSupplyBgm.setFlag(true);
        executorService.execute(musicGetSupplyBgm);
    }

    public void bossBgm(){
        musicThreadBossBgm.setFlag(bossBgmFlag);
        executorServiceBossBgm.scheduleWithFixedDelay(musicThreadBossBgm,40,40,TimeUnit.MILLISECONDS);
    }

    public void setBossBgmFlag(boolean bossBgmFlag) {
        this.bossBgmFlag = bossBgmFlag;
    }

    public void setGameBgmFlag(boolean gameBgmFlag) {
        this.gameBgmFlag = gameBgmFlag;
    }
}
