use adw::prelude::*;
use relm4::prelude::*;

pub struct Model;
pub struct Init;

#[derive(Debug)]
pub enum Input {}

#[derive(Debug)]
pub enum Output {}

#[relm4::component(pub)]
impl SimpleComponent for Model {
    type Init = Init;
    type Input = Input;
    type Output = Output;

    view! {
        adw::Window {
            set_title: Some("Help"),
            adw::HeaderBar,
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        _sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = Self;
        let widgets = view_output!();
        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, _sender: ComponentSender<Self>) {
        match message {}
    }
}
