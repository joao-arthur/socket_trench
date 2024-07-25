use relm4::adw::prelude::*;
use relm4::prelude::*;
use relm4::{adw, gtk};

relm4::new_action_group!(pub ContentActions, "content");
relm4::new_stateless_action!(pub CreateMatch, ContentActions, "creatematch");

pub struct ContentModel;
pub struct ContentInit;

#[derive(Debug)]
pub enum ContentInput {
    CreateMatch,
    ConnectMatch,
}

#[derive(Debug)]
pub enum ContentOutput {}

#[relm4::component(pub)]
impl SimpleComponent for ContentModel {
    type Init = ContentInit;
    type Input = ContentInput;
    type Output = ContentOutput;

    view! {
        #[root]
        gtk::Box {
            set_orientation: gtk::Orientation::Vertical,
            set_hexpand: true,
            set_margin_all: 32,

            gtk::Label {
                set_label: "Socket Trench",
                set_margin_all: 32,
                set_css_classes: &["title-1"],
            },
            adw::PreferencesGroup {
                adw::ActionRow {
                    set_title: "Criar partida",
                    set_activatable: true,
                    add_suffix = &gtk::Image {
                        set_icon_name: Some("go-next-symbolic")
                    },
                    connect_activated[sender] => move |_| {
                        sender.input(ContentInput::CreateMatch);
                    },
                },
                adw::ActionRow {
                    set_title: "Conectar à partida",
                    set_activatable: true,
                    add_suffix = &gtk::Image {
                        set_icon_name: Some("go-next-symbolic")
                    },
                    connect_activated[sender] => move |_| {
                        sender.input(ContentInput::ConnectMatch);
                    },
                },
            },
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = Self;
        let widgets = view_output!();
        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, _: ComponentSender<Self>) {
        match message {
            ContentInput::CreateMatch => println!("CreateMatch"),
            ContentInput::ConnectMatch => println!("ConnectMatch"),
        }
    }
}
