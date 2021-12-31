import chisel3._
import chisel3.util._

/* 
Module 默认高电平复位，在Xyloni上两个按键默认高电平
所以，需要使用RawModule进行翻转
 */

class blink extends Module
{
    val io = IO(new Bundle{ 
        val led = Output(UInt(4.W))
        val sw  = Input(Bool())
    })

    val MAX_CNT = (50000000 / 2 - 1).U

    val reg_cnt = RegInit(0.U(32.W))
    val reg_blk = RegInit(0.U(1.W))

    reg_cnt := reg_cnt + 1.U
    when (reg_cnt === MAX_CNT)
    {
        reg_cnt := 0.U
        reg_blk := ~reg_blk
        
    }
    io.led := Cat(reg_blk, reg_blk, reg_blk & ~io.sw, reg_blk & ~io.sw)
}

class blink_wrapper extends RawModule
{
    val clk  = IO(Input(Clock()))
    val rstn = IO(Input(Bool()))
    val led  = IO(Output(UInt(4.W)))
    val sw   = IO(Input(Bool()))

    val led_flasher = withClockAndReset(clk, !rstn) 
    {
        Module(new blink)
    }

    led_flasher.io.sw := sw
    led := led_flasher.io.led
}

object blink extends App
{
    (new chisel3.stage.ChiselStage).emitVerilog(new blink_wrapper())
}